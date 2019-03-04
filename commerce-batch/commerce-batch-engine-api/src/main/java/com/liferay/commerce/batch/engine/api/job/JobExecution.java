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

package com.liferay.commerce.batch.engine.api.job;

import java.util.Objects;

/**
 * @author Ivica Cardic
 */
public class JobExecution {

	public JobExecution(JobInstance jobInstance, JobParameters jobParameters) {
		_jobInstance = Objects.requireNonNull(jobInstance);
		_jobParameters = jobParameters;
	}

	public JobInstance getJobInstance() {
		return _jobInstance;
	}

	public JobParameters getJobParameters() {
		return _jobParameters;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("JobExecution{jobInstance=");
		sb.append(_jobInstance);
		sb.append(", jobParameters=");
		sb.append(_jobParameters);
		sb.append("}");

		return sb.toString();
	}

	private final JobInstance _jobInstance;
	private final JobParameters _jobParameters;

}