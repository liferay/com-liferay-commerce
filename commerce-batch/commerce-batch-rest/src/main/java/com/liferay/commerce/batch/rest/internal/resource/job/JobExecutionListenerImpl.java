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

package com.liferay.commerce.batch.rest.internal.resource.job;

import com.liferay.commerce.batch.engine.api.item.ItemReader;
import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobExecutionListener;

/**
 * @author Ivica Cardic
 */
public class JobExecutionListenerImpl implements JobExecutionListener {

	public JobExecutionListenerImpl(
		ItemRegistry itemRegistry, ItemReader itemReader,
		ItemWriter itemWriter) {

		_itemRegistry = itemRegistry;
		_itemReader = itemReader;
		_itemWriter = itemWriter;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		_itemRegistry.unget(_itemReader);
		_itemRegistry.unget(_itemWriter);
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
	}

	private final ItemReader _itemReader;
	private final ItemRegistry _itemRegistry;
	private final ItemWriter _itemWriter;

}