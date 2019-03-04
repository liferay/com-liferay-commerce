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
import com.liferay.commerce.batch.engine.api.job.BatchStatus;
import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobExecutionListener;
import com.liferay.commerce.batch.engine.api.job.JobInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ivica Cardic
 */
public class JobImpl implements Job {

	public JobImpl(
		String key, String name, ItemReader itemReader, ItemWriter itemWriter) {

		_key = Objects.requireNonNull(key);
		_name = Objects.requireNonNull(name);
		_itemReader = Objects.requireNonNull(itemReader);
		_itemWriter = Objects.requireNonNull(itemWriter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void execute(JobExecution jobExecution) throws Exception {
		Objects.requireNonNull(jobExecution);

		JobInstance jobInstance = jobExecution.getJobInstance();

		jobInstance.setBatchStatus(BatchStatus.STARTED);

		try {
			for (JobExecutionListener jobExecutionListener :
					_jobExecutionListeners) {

				jobExecutionListener.beforeJob(jobExecution);
			}

			List items = new ArrayList();

			Object item = null;

			while ((item = _itemReader.read()) != null) {
				items.add(item);
			}

			_itemWriter.write(items);

			for (JobExecutionListener jobExecutionListener :
					_jobExecutionListeners) {

				jobExecutionListener.afterJob(jobExecution);
			}

			jobInstance.setBatchStatus(BatchStatus.COMPLETED);
		}
		catch (Exception e) {
			try {
				for (JobExecutionListener jobExecutionListener :
						_jobExecutionListeners) {

					jobExecutionListener.afterJob(jobExecution);
				}
			}
			finally {
				jobInstance.setBatchStatus(BatchStatus.FAILED);
			}

			throw e;
		}
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void registerJobExecutionListener(
		JobExecutionListener jobExecutionListener) {

		_jobExecutionListeners.add(
			Objects.requireNonNull(jobExecutionListener));
	}

	private final ItemReader _itemReader;
	private final ItemWriter _itemWriter;
	private List<JobExecutionListener> _jobExecutionListeners =
		new ArrayList<>();
	private final String _key;
	private final String _name;

}