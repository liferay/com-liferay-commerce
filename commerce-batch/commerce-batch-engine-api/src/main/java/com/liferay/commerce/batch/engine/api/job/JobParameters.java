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
import java.util.Objects;

/**
 * @author Ivica Cardic
 */
public class JobParameters {

	public JobParameters() {
		_parameters = new LinkedHashMap<>();
	}

	public JobParameters(Map<String, Object> parameters) {
		_parameters = new LinkedHashMap<>(Objects.requireNonNull(parameters));
	}

	public <T> T get(String key) {
		return (T)_parameters.get(key);
	}

	public Date getDate(String key) {
		return getDate(key, null);
	}

	public Date getDate(String key, Date defaultValue) {
		if (_parameters.containsKey(key)) {
			return (Date)_parameters.get(key);
		}

		return defaultValue;
	}

	public Double getDouble(String key) {
		if (!_parameters.containsKey(key)) {
			return null;
		}

		return (Double)_parameters.get(key);
	}

	public Double getDouble(String key, Double defaultValue) {
		if (_parameters.containsKey(key)) {
			return getDouble(key);
		}

		return defaultValue;
	}

	public Long getLong(String key) {
		if (!_parameters.containsKey(key)) {
			return null;
		}

		Object value = _parameters.get(key);

		if (value == null) {
			return null;
		}

		return (Long)value;
	}

	public Long getLong(String key, Long defaultValue) {
		if (_parameters.containsKey(key)) {
			return getLong(key);
		}

		return defaultValue;
	}

	public Map<String, Object> getParameters() {
		return new LinkedHashMap<>(_parameters);
	}

	public String getString(String key) {
		Object value = _parameters.get(key);

		if (value == null) {
			return null;
		}

		return value.toString();
	}

	public String getString(String key, String defaultValue) {
		if (_parameters.containsKey(key)) {
			return getString(key);
		}

		return defaultValue;
	}

	public boolean isEmpty() {
		return _parameters.isEmpty();
	}

	public JobParameters put(String key, Object value) {
		_parameters.put(key, value);

		return this;
	}

	@Override
	public String toString() {
		return _parameters.toString();
	}

	private final Map<String, Object> _parameters;

}