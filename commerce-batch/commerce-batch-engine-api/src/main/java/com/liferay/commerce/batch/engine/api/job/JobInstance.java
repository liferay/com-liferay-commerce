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

	public JobInstance(String id, String jobName) {
		_id = id;
		_jobName = jobName;
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

		return _id.equals(that._id);
	}

	public String getId() {
		return _id;
	}

	public String getJobName() {
		return _jobName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("JobInstance{id=");
		sb.append(_id);
		sb.append(", jobName=");
		sb.append(_jobName);
		sb.append("}");

		return sb.toString();
	}

	private final String _id;
	private final String _jobName;

}