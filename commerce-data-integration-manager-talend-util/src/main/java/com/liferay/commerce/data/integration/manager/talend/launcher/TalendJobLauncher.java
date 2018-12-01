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

package com.liferay.commerce.data.integration.manager.talend.launcher;

import com.liferay.commerce.data.integration.manager.model.Process;
import com.liferay.commerce.data.integration.manager.model.ScheduledTask;
import com.liferay.commerce.data.integration.manager.service.HistoryLocalService;
import com.liferay.commerce.data.integration.manager.service.ProcessService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskExectutorService;
import com.liferay.commerce.data.integration.manager.service.ScheduledTaskLocalService;
import com.liferay.commerce.data.integration.manager.talend.util.DLManagementUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.commerce.data.integration.manager.talend.launcher.configuration.TalendJobExecutorConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author guywandji
 */
@Component(
	configurationPid = "com.liferay.talend.job.launcher.TalendJobExecutorConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = {
		"data.integration.service.executor.key=" + TalendJobLauncher.KEY,
		"osgi.command.function=runJob", "osgi.command.function=runJob"
	},
	service = ScheduledTaskExectutorService.class
)
public class TalendJobLauncher implements ScheduledTaskExectutorService {

	public static final String KEY = "talend";

	/* The talend job is executed
	 */
	public boolean execute(
			long userId, ScheduledTask scheduledTask, String executionType,
			Date startDate, File error, File log)
		throws InterruptedException, IOException, PortalException {

		Date endDate = new Date();

		long scheduledTaskId = scheduledTask.getScheduledTaskId();

		ProcessBuilder pb = new ProcessBuilder(_command);

		File jarNameFile = new File(_jarName);

		pb.redirectError(error);
		pb.redirectOutput(log);
		pb.directory(new File(jarNameFile.getParent()));

		java.lang.Process pr = pb.start();

		pr.waitFor();

		DLFileEntry errorLogFileEntry = _dlManagementUtil.addFileEntry(
			userId, scheduledTask.getCompanyId(), scheduledTask.getGroupId(),
			scheduledTask.getName(), scheduledTask.getName() + "_error",
			ContentTypes.APPLICATION_TEXT, error);

		DLFileEntry runtimeLogFileEntry = _dlManagementUtil.addFileEntry(
			userId, scheduledTask.getCompanyId(), scheduledTask.getGroupId(),
			scheduledTask.getName(), scheduledTask.getName() + "_runtime",
			ContentTypes.APPLICATION_TEXT, log);

		historyLocalService.addHistory(
			userId, scheduledTask.getScheduledTaskId(), executionType,
			startDate, endDate, WorkflowConstants.STATUS_APPROVED,
			errorLogFileEntry.getFileEntryId(),
			runtimeLogFileEntry.getFileEntryId());

		scheduledTaskLocalService.stopScheduledTask(userId, scheduledTaskId);

		return false;
	}

	@Override
	public String getName() {
		return "talend";
	}

	public boolean init(
			long contextFileEntryId, long archiveProcessFileEntryId,
			String version, String className)
		throws Exception {

		FileEntry propsFileEntry = null;

		try {
			if (contextFileEntryId > 0) {
				propsFileEntry = _dlAppLocalService.getFileEntry(
					contextFileEntryId);
				_props = new Properties();

				InputStream inputStream = propsFileEntry.getContentStream();

				_props.load(inputStream);

				inputStream.close();
			}

			FileEntry archiveFileEntry = _dlAppLocalService.getFileEntry(
				archiveProcessFileEntryId);

			InputStream archiveInputStream =
				archiveFileEntry.getContentStream();

			byte[] buffer = new byte[8 * 1024];

			File tempArchiveFile = FileUtil.createTempFile();

			OutputStream outStream = new FileOutputStream(tempArchiveFile);

			int bytesRead;

			while ((bytesRead = archiveInputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			archiveInputStream.close();

			outStream.close();

			_rootDirectoryName = _dlManagementUtil.unzipFile(tempArchiveFile);

			_jarName = _rootDirectoryName + _buildJarName(className, version);

			_libDirectoryName =
				_rootDirectoryName + _configuration.libFolderName();

			if (_PARM_ENVIRONMENT_FIRST) {
				_evaluateEnvironment();
			}

			_jarCommandArgs = _buildJarCommandArgs();

			_buildCommand();
		}
		catch (Exception ex) {
			_log.error(ex);

			return false;
		}

		return true;
	}

	public void runJob() {
		try {
			_libDirectoryName =
				"/Users/guywandji/Documents/buildCatalog_1.0/lib";

			_className = "product_mapping.buildcatalog_1_0.buildCatalog";

			_jarName =
				"/Users/guywandji/Documents/buildCatalog_1.0/buildCatalog" +
					"/buildcatalog_1_0.jar";
			boolean init = init(
				_CONFIGURATION_FILE_ENTRY_ID, _ARCHIVE_FILE_ENTRY_ID, "1.0",
				_className);

			if (init) {
				ProcessBuilder pb = new ProcessBuilder(
					"java", _configuration.xms(), _configuration.xmx(), _CP,
					_jarCommandArgs, _className, "--context=Default", "\"$@\"");

				File log = FileUtil.createTempFile();

				File error = FileUtil.createTempFile();
				File jarNameFile = new File(_jarName);

				pb.redirectError(error);
				pb.redirectOutput(log);
				pb.directory(new File(jarNameFile.getParent()));
				pb.start();
			}
		}
		catch (Exception e) {
			_log.error(e);

			e.printStackTrace();
		}
	}

	@Override
	public void runProcess(
			long userId, long scheduledTaskId, String executionType)
		throws IOException, PortalException {

		Process process;

		ScheduledTask scheduledTask = null;

		Date startDate = new Date();

		File log = FileUtil.createTempFile();

		File error = FileUtil.createTempFile();

		try {
			scheduledTask = scheduledTaskLocalService.getScheduledTask(
				scheduledTaskId);

			process = processService.getProcess(
				userId, scheduledTask.getProcessId());

			_className = process.getClassName();

			/*

			if (scheduledTask.isActive()) {
				return;
			}*/

			boolean init = init(
				process.getContextPropertiesFileEntryId(),
				process.getSrcArchiveFileEntryId(), process.getVersion(),
				process.getClassName());

			if (init) {
				execute(
					userId, scheduledTask, executionType, startDate, error,
					log);
			}
			else {
				scheduledTaskLocalService.stopScheduledTask(
					userId, scheduledTaskId);
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e);
			}

			Date endDate = new Date();

			scheduledTaskLocalService.stopScheduledTask(
				userId, scheduledTaskId);

			DLFileEntry errorLogFileEntry = _dlManagementUtil.addFileEntry(
				userId, scheduledTask.getCompanyId(),
				scheduledTask.getGroupId(), scheduledTask.getName(),
				scheduledTask.getName() + "_error",
				ContentTypes.APPLICATION_TEXT, error);

			historyLocalService.addHistory(
				userId, scheduledTaskId, executionType, startDate, endDate,
				WorkflowConstants.STATUS_DENIED,
				errorLogFileEntry.getFileEntryId(), 0L);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			TalendJobExecutorConfiguration.class, properties);
	}

	@Reference
	protected HistoryLocalService historyLocalService;

	@Reference
	protected ProcessService processService;

	@Reference
	protected ScheduledTaskLocalService scheduledTaskLocalService;

	private void _buildCommand() {
		_command = new ArrayList<>();

		_command.add("java");
		_command.add(_configuration.xms());
		_command.add(_configuration.xmx());
		_command.add(_CP);
		_command.add(_jarCommandArgs);
		_command.add(_className);
		_command.add(_configuration.endCommandChar());
		_command.add("--context=" + _configuration.context());

		if (_props != null) {
			StringBundler paramSB = new StringBundler();

			for (Object key : _props.keySet()) {
				paramSB.append("--context_param ");
				paramSB.append(key);
				paramSB.append("=");
				paramSB.append(_props.get(key));

				_command.add(paramSB.toString());

				paramSB = new StringBundler();
			}
		}
	}

	private String _buildJarCommandArgs() {
		StringBundler commandSB = new StringBundler();

		commandSB.append(".:");

		if (_libDirectoryName != null) {
			File rootPathAsFileObject = new File(_libDirectoryName);

			if (rootPathAsFileObject.isDirectory()) {
				for (String file : rootPathAsFileObject.list()) {
					commandSB.append(_libDirectoryName);
					commandSB.append("/");
					commandSB.append(file);
					commandSB.append(":");
				}
			}
		}

		commandSB.append(_jarName);
		commandSB.append(":");

		return commandSB.toString();
	}

	private String _buildJarName(String className, String version) {
		String jarName = "";

		int lastIndexOfDot = className.lastIndexOf(".");

		if (lastIndexOfDot > 0) {
			jarName = className.substring(lastIndexOfDot + 1);
		}

		StringBuilder jarNameBuilder = new StringBuilder();

		jarNameBuilder.append("/");
		jarNameBuilder.append(jarName);
		jarNameBuilder.append("/");
		jarNameBuilder.append(jarName);
		jarNameBuilder.append("_");
		jarNameBuilder.append(version.replace(".", "_"));
		jarNameBuilder.append(".jar");

		return jarNameBuilder.toString();
	}

	private void _evaluateEnvironment() {
		Map<String, String> env = System.getenv();

		for (String key : env.keySet()) {
			if (key.startsWith(_PARM_TOS_QUALIFIER + _PARM_DELIMITER)) {
				key = key.substring(
					(_PARM_TOS_QUALIFIER + _PARM_DELIMITER).length());
			}
		}
	}

	private static final long _ARCHIVE_FILE_ENTRY_ID = 55332;

	private static final long _CONFIGURATION_FILE_ENTRY_ID = 55341;

	private static final String _CP = "-cp";

	private static final String _PARM_DELIMITER = "_";

	private static final Boolean _PARM_ENVIRONMENT_FIRST = true;

	private static final String _PARM_TOS_QUALIFIER = "TOS";

	private static final Log _log = LogFactoryUtil.getLog(
		TalendJobLauncher.class);

	private String _className;
	private List<String> _command;
	private volatile TalendJobExecutorConfiguration _configuration;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLManagementUtil _dlManagementUtil;

	private String _jarCommandArgs;
	private String _jarName;
	private String _libDirectoryName;
	private Properties _props;
	private String _rootDirectoryName;

}