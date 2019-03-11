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
import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.engine.api.job.BatchStatus;
import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobExecutionListener;
import com.liferay.commerce.batch.model.CommerceBatchJob;
import com.liferay.commerce.batch.service.CommerceBatchJobLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(factory = "JobImpl", service = Job.class)
public class JobImpl implements Job {

	@Override
	public void dispose() {
		_itemRegistry.unget(_itemReader);
		_itemRegistry.unget(_itemWriter);
	}

	@Override
	public void execute(JobExecution jobExecution) throws Exception {
		Objects.requireNonNull(jobExecution);

		invokeTransaction(() -> _execute(jobExecution));
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

	public void setItemReader(ItemReader itemReader) {
		_itemReader = itemReader;
	}

	public void setItemWriter(ItemWriter itemWriter) {
		_itemWriter = itemWriter;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setName(String name) {
		_name = name;
	}

	protected void invokeTransaction(Callable<Void> callable) throws Exception {
		try {
			TransactionInvokerUtil.invoke(_transactionConfig, callable);
		}
		catch (Throwable t) {
			throw new Exception(t);
		}
	}

	@SuppressWarnings("unchecked")
	private Void _execute(JobExecution jobExecution) throws Exception {
		try {
			_startCommerceBatchJob(jobExecution);

			_executeBeforeJobListeners(jobExecution);

			List items = new ArrayList();

			Object item = null;

			while ((item = _itemReader.read()) != null) {
				items.add(item);
			}

			_itemWriter.write(items);

			_finishCommerceBatchJob(
				jobExecution.getCommerceBatchJob(), BatchStatus.COMPLETED);

			_executeAfterJobListeners(jobExecution);
		}
		catch (Exception e) {
			_finishCommerceBatchJob(
				jobExecution.getCommerceBatchJob(), BatchStatus.FAILED);

			_executeAfterJobListeners(jobExecution);

			throw e;
		}
		finally {
			if (_itemReader.getInputStream() != null) {
				try {
					InputStream inputStream = _itemReader.getInputStream();

					inputStream.close();
				}
				catch (IOException ioe) {
					_log.error(ioe.getMessage(), ioe);
				}
			}
		}

		return null;
	}

	private void _executeAfterJobListeners(JobExecution jobExecution) {
		for (JobExecutionListener jobExecutionListener :
				_jobExecutionListeners) {

			jobExecutionListener.afterJob(jobExecution);
		}
	}

	private void _executeBeforeJobListeners(JobExecution jobExecution) {
		for (JobExecutionListener jobExecutionListener :
				_jobExecutionListeners) {

			jobExecutionListener.beforeJob(jobExecution);
		}
	}

	private void _finishCommerceBatchJob(
		CommerceBatchJob commerceBatchJob, BatchStatus batchStatus) {

		commerceBatchJob.setStatus(batchStatus.toString());
		commerceBatchJob.setEndTime(new Date());

		_commerceBatchJobLocalService.updateCommerceBatchJob(commerceBatchJob);
	}

	private void _startCommerceBatchJob(JobExecution jobExecution) {
		CommerceBatchJob commerceBatchJob = jobExecution.getCommerceBatchJob();

		commerceBatchJob.setStatus(BatchStatus.STARTED.toString());
		commerceBatchJob.setStartTime(new Date());

		_commerceBatchJobLocalService.updateCommerceBatchJob(commerceBatchJob);
	}

	private static final Log _log = LogFactoryUtil.getLog(JobImpl.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRES_NEW, new Class<?>[] {Exception.class});

	@Reference
	private CommerceBatchJobLocalService _commerceBatchJobLocalService;

	private ItemReader _itemReader;

	@Reference
	private ItemRegistry _itemRegistry;

	private ItemWriter _itemWriter;
	private final List<JobExecutionListener> _jobExecutionListeners =
		new ArrayList<>();
	private String _key;
	private String _name;

}