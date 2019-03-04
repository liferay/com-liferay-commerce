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
public class JobInstance {

	public JobInstance(String jobKey, String jobName) {
		_jobKey = Objects.requireNonNull(jobKey);
		_jobName = Objects.requireNonNull(jobName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		JobInstance that = (JobInstance)o;

		return _jobKey.equals(that._jobKey);
	}

	public BatchStatus getBatchStatus() {
		return _batchStatus;
	}

	public String getJobKey() {
		return _jobKey;
	}

	public String getJobName() {
		return _jobName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_jobKey);
	}

	public void setBatchStatus(BatchStatus batchStatus) {
		_batchStatus = batchStatus;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("JobInstance{batchStatus=");
		sb.append(_batchStatus);
		sb.append(", id=");
		sb.append(_jobKey);
		sb.append(", jobName=");
		sb.append(_jobName);
		sb.append("}");

		return sb.toString();
	}

	private BatchStatus _batchStatus = BatchStatus.UNKNOWN;
	private final String _jobKey;
	private final String _jobName;

}