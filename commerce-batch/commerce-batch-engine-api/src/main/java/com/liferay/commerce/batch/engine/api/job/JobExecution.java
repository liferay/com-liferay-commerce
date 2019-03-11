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

import com.liferay.commerce.batch.model.CommerceBatchJob;

import java.util.Objects;

/**
 * @author Ivica Cardic
 */
public class JobExecution {

	public JobExecution(
		CommerceBatchJob commerceBatchJob, JobParameters jobParameters) {

		_commerceBatchJob = Objects.requireNonNull(commerceBatchJob);
		_jobParameters = jobParameters;
	}

	public CommerceBatchJob getCommerceBatchJob() {
		return _commerceBatchJob;
	}

	public JobParameters getJobParameters() {
		return _jobParameters;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("JobExecution{commerceBatchJob=");
		sb.append(_commerceBatchJob);
		sb.append(", jobParameters=");
		sb.append(_jobParameters);
		sb.append("}");

		return sb.toString();
	}

	private final CommerceBatchJob _commerceBatchJob;
	private final JobParameters _jobParameters;

}