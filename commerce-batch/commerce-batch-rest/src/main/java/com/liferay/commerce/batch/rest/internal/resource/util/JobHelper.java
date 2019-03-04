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

package com.liferay.commerce.batch.rest.internal.resource.util;

import com.liferay.commerce.batch.engine.api.item.FileAwareItemReader;
import com.liferay.commerce.batch.engine.api.item.ItemReader;
import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.engine.api.item.Operation;
import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobFactory;
import com.liferay.commerce.batch.engine.api.job.JobInstance;
import com.liferay.commerce.batch.engine.api.job.JobLauncher;
import com.liferay.commerce.batch.rest.internal.resource.job.JobExecutionListenerImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = JobHelper.class)
public class JobHelper {

	public boolean getBatchExecutionStatus(String jobId) {
		return _jobLauncher.isJobActive(jobId);
	}

	public String run(Attachment attachment) throws Exception {
		JobExecution jobExecution = _jobLauncher.run(
			_createJob(attachment), null);

		JobInstance jobInstance = jobExecution.getJobInstance();

		return jobInstance.getId();
	}

	private Job _createJob(Attachment attachment) throws Exception {
		String filename = _getFilename(attachment);

		if (_log.isDebugEnabled()) {
			_log.debug("Importing file " + filename);
		}

		ItemComponent itemComponent = _getItemComponent(filename);

		ItemReader itemReader = _getItemReader(
			itemComponent._type, itemComponent._version,
			itemComponent._operation, attachment, filename);

		ItemWriter itemWriter = _itemRegistry.get(
			itemComponent._type, itemComponent._version,
			itemComponent._operation, ItemWriter.class);

		Job job = _jobFactory.create(filename, itemReader, itemWriter);

		job.registerJobExecutionListener(
			new JobExecutionListenerImpl(
				_itemRegistry, itemReader, itemWriter));

		return job;
	}

	private String _getFilename(Attachment attachment) {
		DataHandler dataHandler = attachment.getDataHandler();

		return dataHandler.getName();
	}

	private ItemComponent _getItemComponent(String filename) {
		ItemComponent itemComponent = new ItemComponent();

		String[] items = filename.split("\\.");

		items = items[0].split("_");

		itemComponent._type = items[0];
		itemComponent._version = items[1] + "." + items[2];
		itemComponent._operation = Operation.valueOf(items[3]);

		return itemComponent;
	}

	private ItemReader _getItemReader(
			String type, String version, Operation operation,
			Attachment attachment, String filename)
		throws Exception {

		FileAwareItemReader fileAwareItemReader = _itemRegistry.get(
			type, version, operation, FileAwareItemReader.class);

		fileAwareItemReader.setFile(_writeToTempFile(attachment));
		fileAwareItemReader.setOriginalFilename(filename);

		return fileAwareItemReader;
	}

	private File _writeToTempFile(Attachment attachment) throws IOException {
		DataHandler dataHandler = attachment.getDataHandler();

		File tempFile = File.createTempFile(dataHandler.getName(), ".json");

		attachment.transferTo(tempFile);

		return tempFile;
	}

	private static final Log _log = LogFactoryUtil.getLog(JobHelper.class);

	@Reference
	private ItemRegistry _itemRegistry;

	@Reference
	private JobFactory _jobFactory;

	@Reference
	private JobLauncher _jobLauncher;

	private static class ItemComponent {

		private Operation _operation;
		private String _type;
		private String _version;

	}

}