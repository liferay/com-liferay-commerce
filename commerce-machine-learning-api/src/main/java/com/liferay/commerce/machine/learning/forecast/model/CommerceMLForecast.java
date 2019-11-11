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

package com.liferay.commerce.machine.learning.forecast.model;

import aQute.bnd.annotation.ProviderType;

import java.util.Date;

/**
 * @author Riccardo Ferrari
 */
@ProviderType
public interface CommerceMLForecast {

	public float getActual();

	public long getCompanyId();

	public float getForecast();

	public long getForecastId();

	public float getForecastLowerBound();

	public float getForecastUpperBound();

	public String getJobId();

	public String getPeriod();

	public String getScope();

	public String getTarget();

	public Date getTimestamp();

	public void setActual(float actual);

	public void setCompanyId(long companyId);

	public void setForecast(float forecast);

	public void setForecastId(long forecastId);

	public void setForecastLowerBound(float forecastLowerBound);

	public void setForecastUpperBound(float forecastUpperBound);

	public void setJobId(String jobId);

	public void setPeriod(String period);

	public void setScope(String scope);

	public void setTarget(String target);

	public void setTimestamp(Date timestamp);

}