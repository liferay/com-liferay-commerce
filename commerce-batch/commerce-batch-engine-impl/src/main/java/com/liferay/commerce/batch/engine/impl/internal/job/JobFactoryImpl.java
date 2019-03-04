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

import com.liferay.commerce.batch.engine.api.item.ItemReader;
import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobFactory;

import java.util.UUID;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = JobFactory.class)
public class JobFactoryImpl implements JobFactory {

	@Override
	public Job create(
		String name, ItemReader itemReader, ItemWriter itemWriter) {

		return new JobImpl(_generateJobKey(), name, itemReader, itemWriter);
	}

	@Override
	public Job create(
		String id, String name, ItemReader itemReader, ItemWriter itemWriter) {

		return new JobImpl(id, name, itemReader, itemWriter);
	}

	private String _generateJobKey() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

}