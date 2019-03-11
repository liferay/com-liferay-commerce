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

package com.liferay.commerce.batch.engine.impl.internal.batch;

import com.liferay.commerce.batch.engine.api.batch.BatchFileProcessor;
import com.liferay.commerce.batch.engine.api.exception.NoSuchItemException;
import com.liferay.commerce.batch.engine.api.item.ItemComponent;
import com.liferay.commerce.batch.engine.api.item.ItemComponentFactory;
import com.liferay.commerce.batch.engine.api.item.ItemReader;
import com.liferay.commerce.batch.engine.api.item.ItemRegistry;
import com.liferay.commerce.batch.engine.api.item.ItemWriter;
import com.liferay.commerce.batch.engine.api.item.Operation;
import com.liferay.commerce.batch.engine.api.job.Job;
import com.liferay.commerce.batch.engine.api.job.JobExecution;
import com.liferay.commerce.batch.engine.api.job.JobFactory;
import com.liferay.commerce.batch.engine.api.job.JobLauncher;
import com.liferay.commerce.batch.engine.impl.internal.job.JobExecutionListenerImpl;
import com.liferay.commerce.batch.model.CommerceBatchJob;
import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = BatchFileProcessor.class)
public class BatchFileProcessorImpl implements BatchFileProcessor {

	@Override
	public boolean getBatchExecutionStatus(String key) {
		return _jobLauncher.isJobActive(key);
	}

	public Map<String, String> process(String fileName, InputStream inputStream)
		throws Exception {

		Map<String, String> jobKeyNameMap = new HashMap<>();

		List<Job> jobs = _createJobs(fileName, inputStream);

		for (Job job : jobs) {
			JobExecution jobExecution = _jobLauncher.run(job, null);

			CommerceBatchJob commerceBatchJob =
				jobExecution.getCommerceBatchJob();

			jobKeyNameMap.put(
				commerceBatchJob.getKey(), commerceBatchJob.getName());
		}

		return jobKeyNameMap;
	}

	private List<FileEntry> _addFileEntries(
			String fileName, InputStream inputStream)
		throws Exception {

		List<FileEntry> fileEntries = new ArrayList<>();

		long groupId = _getGroupId();

		if (_isCompressed(fileName)) {
			fileEntries.addAll(_processZipFile(groupId, inputStream));
		}
		else {
			fileEntries.add(
				_addFileEntry(
					fileName, groupId, FileUtil.getBytes(inputStream)));
		}

		return fileEntries;
	}

	private FileEntry _addFileEntry(
			String fileName, long groupId, byte[] byteArray)
		throws PortalException {

		FileEntry fileEntry = null;

		try {
			fileEntry = _dlAppService.getFileEntry(
				groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName);
		}
		catch (NoSuchFileEntryException nsfee) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsfee.getMessage(), nsfee);
			}
		}

		if (fileEntry != null) {
			_dlAppService.deleteFileEntry(fileEntry.getFileEntryId());
		}

		return _dlAppService.addFileEntry(
			groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName,
			ContentTypes.APPLICATION_JSON, fileName, StringPool.BLANK,
			StringPool.BLANK, byteArray, _getServiceContext());
	}

	private List<Job> _createJobs(String fileName, InputStream inputStream)
		throws Exception {

		List<Job> jobs = new ArrayList<>();

		if (_log.isDebugEnabled()) {
			_log.debug("Importing file " + fileName);
		}

		List<FileEntry> fileEntries = _addFileEntries(fileName, inputStream);

		try {
			for (FileEntry fileEntry : fileEntries) {
				ItemComponent itemComponent =
					_itemComponentFactory.getItemComponent(
						fileEntry.getFileName());

				ItemReader itemReader = _getItemReader(
					itemComponent.getType(), itemComponent.getVersion(),
					itemComponent.getOperation(), fileEntry.getContentStream());

				ItemWriter itemWriter = _itemRegistry.get(
					itemComponent.getType(), itemComponent.getVersion(),
					itemComponent.getOperation(), ItemWriter.class);

				Job job = _jobFactory.create(
					fileEntry.getUuid(), fileEntry.getFileName(), itemReader,
					itemWriter);

				job.registerJobExecutionListener(
					new JobExecutionListenerImpl(
						_itemRegistry, itemReader, itemWriter));

				jobs.add(job);
			}
		}
		catch (NoSuchItemException nsie) {
			for (FileEntry fileEntry : fileEntries) {
				_dlAppService.deleteFileEntry(fileEntry.getFileEntryId());
			}

			throw nsie;
		}

		return jobs;
	}

	private long _getGroupId() throws PortalException {
		Group group = _groupService.getCompanyGroup(
			_portal.getDefaultCompanyId());

		return group.getGroupId();
	}

	private ItemReader _getItemReader(
			String type, String version, Operation operation,
			InputStream inputStream)
		throws NoSuchItemException {

		ItemReader itemReader = _itemRegistry.get(
			type, version, operation, ItemReader.class);

		itemReader.setInputStream(inputStream);

		return itemReader;
	}

	private ServiceContext _getServiceContext() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(_portal.getDefaultCompanyId());

		return serviceContext;
	}

	private boolean _isCompressed(String fileName) {
		return fileName.endsWith(".batch");
	}

	private List<FileEntry> _processZipFile(
			long groupId, InputStream inputStream)
		throws IOException, PortalException {

		List<FileEntry> fileEntries = new ArrayList<>();

		ZipInputStream zipInputStream = new ZipInputStream(
			new BufferedInputStream(inputStream));

		byte[] buffer = new byte[2048];
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();
		ZipEntry zipEntry = null;

		while ((zipEntry = zipInputStream.getNextEntry()) != null) {
			String name = zipEntry.getName();

			if (!name.endsWith(".json")) {
				continue;
			}

			int length;

			while ((length = zipInputStream.read(buffer)) > 0) {
				byteArrayOutputStream.write(buffer, 0, length);
			}

			fileEntries.add(
				_addFileEntry(
					zipEntry.getName(), groupId,
					byteArrayOutputStream.toByteArray()));
		}

		return fileEntries;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BatchFileProcessorImpl.class);

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private GroupService _groupService;

	@Reference
	private ItemComponentFactory _itemComponentFactory;

	@Reference
	private ItemRegistry _itemRegistry;

	@Reference
	private JobFactory _jobFactory;

	@Reference
	private JobLauncher _jobLauncher;

	@Reference
	private Portal _portal;

}
