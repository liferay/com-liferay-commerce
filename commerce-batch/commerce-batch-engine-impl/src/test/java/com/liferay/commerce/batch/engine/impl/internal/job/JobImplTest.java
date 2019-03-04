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
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobExecutionListener;
import com.liferay.commerce.batch.engine.api.job.JobInstance;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Ivica Cardic
 */
public class JobImplTest {

	@Before
	@SuppressWarnings("unchecked")
	public void setUp() {
		_itemReader = Mockito.mock(ItemReader.class);
		_itemWriter = Mockito.mock(ItemWriter.class);

		_jobImpl = new JobImpl("name", _itemReader, _itemWriter);

		_jobExecutionListener = Mockito.mock(JobExecutionListener.class);

		_jobImpl.registerJobExecutionListener(_jobExecutionListener);
	}

	@Test(expected = Exception.class)
	public void testExecuteFailure() throws Exception {
		Mockito.when(
			_itemReader.read()
		).thenThrow(
			new Exception()
		);

		JobInstance jobInstance = Mockito.mock(JobInstance.class);

		JobExecution jobExecution = new JobExecution(jobInstance, null);

		try {
			_jobImpl.execute(jobExecution);
		}
		catch (Exception e) {
			Mockito.verify(
				_itemWriter, Mockito.times(0)
			).write(
				Mockito.anyList()
			);

			Mockito.verify(
				_jobExecutionListener, Mockito.times(1)
			).afterJob(
				jobExecution
			);
			Mockito.verify(
				_jobExecutionListener, Mockito.times(1)
			).beforeJob(
				jobExecution
			);

			Mockito.verify(
				jobInstance
			).setBatchStatus(
				BatchStatus.FAILED
			);
			Mockito.verify(
				jobInstance
			).setBatchStatus(
				BatchStatus.STARTED
			);

			throw e;
		}
	}

	@Test
	public void testExecuteSuccess() throws Exception {
		JobInstance jobInstance = Mockito.mock(JobInstance.class);

		JobExecution jobExecution = new JobExecution(jobInstance, null);

		_jobImpl.execute(jobExecution);

		Mockito.when(
			_itemReader.read()
		).thenReturn(
			new Object()
		);

		Mockito.verify(
			_itemWriter, Mockito.times(1)
		).write(
			Mockito.anyList()
		);

		Mockito.verify(
			_jobExecutionListener, Mockito.times(1)
		).afterJob(
			jobExecution
		);
		Mockito.verify(
			_jobExecutionListener, Mockito.times(1)
		).beforeJob(
			jobExecution
		);

		Mockito.verify(
			jobInstance
		).setBatchStatus(
			BatchStatus.COMPLETED
		);
		Mockito.verify(
			jobInstance
		).setBatchStatus(
			BatchStatus.STARTED
		);
	}

	private ItemReader<Object> _itemReader;
	private ItemWriter<Object> _itemWriter;
	private JobExecutionListener _jobExecutionListener;
	private JobImpl _jobImpl;

}