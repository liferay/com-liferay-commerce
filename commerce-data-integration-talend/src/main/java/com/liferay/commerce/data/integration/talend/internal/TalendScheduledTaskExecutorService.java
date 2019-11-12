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

package com.liferay.commerce.data.integration.talend.internal;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcessLog;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLogLocalService;
import com.liferay.commerce.data.integration.service.ScheduledTaskExecutorService;
import com.liferay.commerce.data.integration.talend.TalendProcessTypeHelper;
import com.liferay.commerce.data.integration.talend.internal.process.type.TalendProcessType;
import com.liferay.commerce.data.integration.trigger.CommerceDataIntegrationProcessTriggerHelper;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true,
	property = "data.integration.service.executor.key=" + TalendProcessType.KEY,
	service = ScheduledTaskExecutorService.class
)
public class TalendScheduledTaskExecutorService
	implements ScheduledTaskExecutorService {

	@Override
	public String getName() {
		return TalendProcessType.KEY;
	}

	@Override
	public void runProcess(long commerceDataIntegrationProcessId)
		throws IOException, PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess = null;
		CommerceDataIntegrationProcessLog commerceDataIntegrationProcessLog =
			null;

		Date startDate = new Date();

		String rootDirectoryName = null;

		try {
			commerceDataIntegrationProcess =
				_commerceDataIntegrationProcessLocalService.
					getCommerceDataIntegrationProcess(
						commerceDataIntegrationProcessId);

			Date lastRunStateDate =
				_commerceDataIntegrationProcessTriggerHelper.
					getPreviousFireTime(commerceDataIntegrationProcessId);

			UnicodeProperties typeSettingsProperties =
				commerceDataIntegrationProcess.getTypeSettingsProperties();

			FileEntry fileEntry = _talendProcessTypeHelper.getFileEntry(
				commerceDataIntegrationProcessId);

			InputStream inputStream = fileEntry.getContentStream();

			File tempFile = FileUtil.createTempFile(inputStream);

			File tempFolder = FileUtil.createTempFolder();

			FileUtil.unzip(tempFile, tempFolder);

			rootDirectoryName = tempFolder.getAbsolutePath();

			String[] strings = FileUtil.find(
				rootDirectoryName, "**\\*.sh", null);

			String sh = strings[0];

			File log = FileUtil.createTempFile();
			File error = FileUtil.createTempFile();

			ProcessBuilder oerm = new ProcessBuilder("chmod", "+x", sh);

			oerm.start();

			List<String> params = new ArrayList<>();

			params.add(sh);

			params.add(
				"--context_param companyId=" +
					commerceDataIntegrationProcess.getCompanyId());

			if (lastRunStateDate != null) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");

				params.add(
					"--context_param lastRunStartDate=" +
						simpleDateFormat.format(lastRunStateDate));
			}

			params.add("--context_param jobWorkDirectory=" + rootDirectoryName);

			if (typeSettingsProperties != null) {
				for (Map.Entry propEntry : typeSettingsProperties.entrySet()) {
					StringBundler contextSB = new StringBundler(4);

					contextSB.append("--context_param ");
					contextSB.append(propEntry.getKey());
					contextSB.append(StringPool.EQUAL);
					contextSB.append(propEntry.getValue());

					params.add(contextSB.toString());
				}
			}

			ProcessBuilder pb = new ProcessBuilder(params);

			pb.redirectError(error);
			pb.redirectOutput(log);

			commerceDataIntegrationProcessLog =
				_commerceDataIntegrationProcessLogLocalService.
					addCommerceDataIntegrationProcessLog(
						commerceDataIntegrationProcess.getUserId(),
						commerceDataIntegrationProcess.
							getCommerceDataIntegrationProcessId(),
						null, null, BackgroundTaskConstants.STATUS_IN_PROGRESS,
						startDate, null);

			Process pr = pb.start();

			pr.waitFor();

			_commerceDataIntegrationProcessLogLocalService.
				updateCommerceDataIntegrationProcessLog(
					commerceDataIntegrationProcessLog.
						getCommerceDataIntegrationProcessLogId(),
					FileUtil.read(error), FileUtil.read(log),
					BackgroundTaskConstants.STATUS_SUCCESSFUL, new Date());
		}
		catch (Exception e) {
			_log.error(e, e);

			_commerceDataIntegrationProcessLogLocalService.
				updateCommerceDataIntegrationProcessLog(
					commerceDataIntegrationProcessLog.
						getCommerceDataIntegrationProcessLogId(),
					e.getMessage(), null, BackgroundTaskConstants.STATUS_FAILED,
					new Date());
		}
		finally {
			FileUtil.deltree(rootDirectoryName);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TalendScheduledTaskExecutorService.class);

	@Reference
	private CommerceDataIntegrationProcessLocalService
		_commerceDataIntegrationProcessLocalService;

	@Reference
	private CommerceDataIntegrationProcessLogLocalService
		_commerceDataIntegrationProcessLogLocalService;

	@Reference
	private CommerceDataIntegrationProcessTriggerHelper
		_commerceDataIntegrationProcessTriggerHelper;

	@Reference
	private TalendProcessTypeHelper _talendProcessTypeHelper;

}