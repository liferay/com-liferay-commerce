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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class JobParametersBuilder {

	public JobParametersBuilder() {
		_parameterMap = new LinkedHashMap<>();
	}

	public JobParametersBuilder add(String key, Object parameter) {
		_parameterMap.put(key, parameter);

		return this;
	}

	public JobParametersBuilder addDate(String key, Date parameter) {
		_parameterMap.put(key, parameter);

		return this;
	}

	public JobParametersBuilder addDouble(String key, Double parameter) {
		_parameterMap.put(key, parameter);

		return this;
	}

	public JobParametersBuilder addLong(String key, Long parameter) {
		_parameterMap.put(key, parameter);

		return this;
	}

	public JobParametersBuilder addString(String key, String parameter) {
		_parameterMap.put(key, parameter);

		return this;
	}

	public JobParameters toJobParameters() {
		return new JobParameters(_parameterMap);
	}

	private final Map<String, Object> _parameterMap;

}