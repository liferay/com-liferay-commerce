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

package com.liferay.data.integration.service.impl;

import com.liferay.data.integration.constants.DataIntegrationConstants;
import com.liferay.data.integration.constants.Frequency;
import com.liferay.data.integration.model.ScheduledTask;
import com.liferay.data.integration.service.base.ScheduledTaskLocalServiceBaseImpl;
import com.liferay.data.integration.service.messaging.DataIntegrationMessageListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.IOException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.osgi.framework.InvalidSyntaxException;

/**
 * The implementation of the scheduled task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.data.integration.service.ScheduledTaskLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ScheduledTaskLocalServiceBaseImpl
 * @see com.liferay.data.integration.service.ScheduledTaskLocalServiceUtil
 */
public class ScheduledTaskLocalServiceImpl
	extends ScheduledTaskLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.data.integration.service.ScheduledTaskLocalServiceUtil} to access the scheduled task local service.
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ScheduledTask addScheduledTask(
			long processId, String frequency, Date startDate, String startHour,
			String name, ServiceContext serviceContext)
		throws PortalException {

		long scheduledTaskId = counterLocalService.increment(
			ScheduledTask.class.getName());

		Date now = new Date();

		User user = userLocalService.getUser(serviceContext.getUserId());

		ScheduledTask scheduledTask = scheduledTaskPersistence.create(
			scheduledTaskId);

		boolean enabled = false;

		enabled = startDate.getTime() <= now.getTime();

		scheduledTask.setCompanyId(serviceContext.getCompanyId());
		scheduledTask.setGroupId(serviceContext.getScopeGroupId());
		scheduledTask.setCreateDate(now);
		scheduledTask.setStartDate(startDate);
		scheduledTask.setStartHour(startHour);
		scheduledTask.setModifiedDate(now);
		scheduledTask.setName(name);
		scheduledTask.setProcessId(processId);
		scheduledTask.setUserId(user.getUserId());
		scheduledTask.setUserName(user.getFullName());
		scheduledTask.setFrequency(frequency);

		scheduledTask.setEnabled(enabled);

		try {
			registerScheduler(scheduledTask);
		}
		catch (InvalidSyntaxException | IOException e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e);
			}
		}

		return scheduledTaskPersistence.update(scheduledTask);
	}

	public int countScheduledTasksByActive(boolean active) {
		return scheduledTaskPersistence.countByActive(active);
	}

	public int countScheduledTasksByActive(long groupId, boolean active) {
		return scheduledTaskPersistence.countByGroupId_Active(groupId, active);
	}

	public int countScheduledTasksByEnabled(boolean enabled) {
		return scheduledTaskPersistence.countByEnabled(enabled);
	}

	public int countScheduledTasksByGroupId_Enabled(
		long groupId, boolean enabled) {

		return scheduledTaskPersistence.countByGroupId_Enabled(
			groupId, enabled);
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public ScheduledTask deleteScheduledTask(long scheduledTaskId)
		throws PortalException {

		ScheduledTask scheduledTask = getScheduledTask(scheduledTaskId);

		unRegisterScheduledTask(scheduledTask);

		return scheduledTaskPersistence.remove(scheduledTaskId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ScheduledTask disableScheduledTask(long scheduledTaskId, long userId)
		throws PortalException {

		Date now = new Date();

		User user = userLocalService.getUser(userId);

		ScheduledTask scheduledTask =
			scheduledTaskPersistence.fetchByPrimaryKey(scheduledTaskId);

		scheduledTask.setEnabled(false);
		scheduledTask.setModifiedDate(now);
		scheduledTask.setUserId(userId);
		scheduledTask.setUserName(user.getFullName());

		unRegisterScheduledTask(scheduledTask);

		return scheduledTaskPersistence.update(scheduledTask);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ScheduledTask enableScheduledTask(long scheduledTaskId, long userId)
		throws PortalException {

		Date now = new Date();

		User user = userLocalService.getUser(userId);

		ScheduledTask scheduledTask =
			scheduledTaskPersistence.fetchByPrimaryKey(scheduledTaskId);

		scheduledTask.setEnabled(true);
		scheduledTask.setModifiedDate(now);
		scheduledTask.setUserId(userId);
		scheduledTask.setUserName(user.getFullName());

		return scheduledTaskPersistence.update(scheduledTask);
	}

	public List<ScheduledTask> getScheduledTaskByGroupId(
		long groupId, int start, int end) {

		return scheduledTaskPersistence.findByGroupId(groupId, start, end);
	}

	public int getScheduledTaskByGroupIdCount(long groupId) {
		return scheduledTaskPersistence.countByGroupId(groupId);
	}

	public List<ScheduledTask> getScheduledTasksByActive(
		boolean active, int start, int end) {

		return scheduledTaskPersistence.findByActive(active, start, end);
	}

	public List<ScheduledTask> getScheduledTasksByEnabled(
		boolean enabled, int start, int end) {

		return scheduledTaskPersistence.findByEnabled(enabled, start, end);
	}

	public List<ScheduledTask> getScheduledTasksByGroupId_Active(
		long groupId, boolean active, int start, int end) {

		return scheduledTaskPersistence.findByGroupId_Active(
			groupId, active, start, end);
	}

	public List<ScheduledTask> getScheduledTasksByGroupId_Enabled(
		long groupId, boolean enabled, int start, int end) {

		return scheduledTaskPersistence.findByGroupId_Enabled(
			groupId, enabled, start, end);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ScheduledTask startScheduledTask(long userId, long scheduledTaskId)
		throws PortalException {

		ScheduledTask scheduledTask =
			scheduledTaskPersistence.fetchByPrimaryKey(scheduledTaskId);

		Date now = new Date();

		User user = userLocalService.getUser(userId);

		scheduledTask.setUserId(user.getUserId());
		scheduledTask.setUserName(user.getFullName());

		scheduledTask.setActive(true);
		scheduledTask.setRunStartDate(now);

		return scheduledTaskPersistence.update(scheduledTask);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ScheduledTask stopScheduledTask(long userId, long scheduledTaskId)
		throws PortalException {

		ScheduledTask scheduledTask =
			scheduledTaskPersistence.fetchByPrimaryKey(scheduledTaskId);

		Date now = new Date();

		User user = userLocalService.getUser(userId);

		scheduledTask.setUserId(user.getUserId());
		scheduledTask.setUserName(user.getFullName());

		scheduledTask.setActive(false);
		scheduledTask.setRunEndDate(now);

		return scheduledTaskPersistence.update(scheduledTask);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ScheduledTask updateScheduledTask(
			long scheduledTaskId, long processId, String frequency,
			Date startDate, String startHour, String name,
			ServiceContext serviceContext)
		throws PortalException {

		Date now = new Date();

		User user = userLocalService.getUser(serviceContext.getUserId());

		ScheduledTask scheduledTask =
			scheduledTaskPersistence.fetchByPrimaryKey(scheduledTaskId);

		boolean enabled = false;

		if (startDate.getTime() <= now.getTime()) {
			enabled = true;
		}

		scheduledTask.setModifiedDate(now);
		scheduledTask.setName(name);
		scheduledTask.setProcessId(processId);
		scheduledTask.setUserId(user.getUserId());
		scheduledTask.setUserName(user.getFullName());
		scheduledTask.setFrequency(frequency);
		scheduledTask.setStartDate(startDate);
		scheduledTask.setStartHour(startHour);
		scheduledTask.setEnabled(enabled);

		try {
			registerScheduler(scheduledTask);
		}
		catch (InvalidSyntaxException | IOException e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e);
			}
		}

		return scheduledTaskPersistence.update(scheduledTask);
	}

	protected void registerScheduler(ScheduledTask scheduledTask)
		throws InvalidSyntaxException, IOException, PortalException {

		Class<?> clazz = DataIntegrationMessageListener.class;

		String className = clazz.getName();

		Group group = _groupLocalService.getGroup(scheduledTask.getGroupId());

		Message message = new Message();

		JSONObject payLoad = JSONFactoryUtil.createJSONObject();

		payLoad.put("executionType", "scheduled");
		payLoad.put("scheduledTaskId", scheduledTask.getScheduledTaskId());
		payLoad.put("userId", scheduledTask.getUserId());

		message.setPayload(payLoad);

		String cronExpression = _getCronExpression(scheduledTask);

		SchedulerResponse schedulerResponse =
			_schedulerEngineHelper.getScheduledJob(
				scheduledTask.getName(), group.getNameCurrentValue(),
				StorageType.PERSISTED);

		SchedulerEntry schedulerEntry;

		if (schedulerResponse != null) {
			schedulerEntry = new SchedulerEntryImpl(
				className, schedulerResponse.getTrigger());

			_schedulerEngineHelper.delete(
				schedulerEntry, StorageType.PERSISTED);

			_schedulerEngineHelper.unschedule(
				scheduledTask.getName(), group.getNameCurrentValue(),
				StorageType.PERSISTED);
		}

		Trigger trigger = _triggerFactory.createTrigger(
			scheduledTask.getName(), group.getNameCurrentValue(), null, null,
			cronExpression);

		schedulerEntry = new SchedulerEntryImpl(className, trigger);

		_schedulerEngineHelper.schedule(
			trigger, StorageType.PERSISTED, scheduledTask.getName(),
			DataIntegrationConstants.DESTINATION_NAME, message, 1000);
	}

	protected void unRegisterScheduledTask(ScheduledTask scheduledTask)
		throws PortalException {

		Class<?> clazz = DataIntegrationMessageListener.class;

		String className = clazz.getName();

		Group group = _groupLocalService.getGroup(scheduledTask.getGroupId());

		SchedulerResponse schedulerResponse =
			_schedulerEngineHelper.getScheduledJob(
				scheduledTask.getName(), group.getNameCurrentValue(),
				StorageType.PERSISTED);

		SchedulerEntry schedulerEntry;

		if (schedulerResponse != null) {
			schedulerEntry = new SchedulerEntryImpl(
				className, schedulerResponse.getTrigger());

			_schedulerEngineHelper.delete(
				schedulerEntry, StorageType.PERSISTED);

			_schedulerEngineHelper.unschedule(
				scheduledTask.getName(), group.getNameCurrentValue(),
				StorageType.PERSISTED);
		}
	}

	private String _getCronExpression(ScheduledTask scheduledTask) {
		Date startDate = scheduledTask.getStartDate();

		String startHour = scheduledTask.getStartHour();

		String frequencyName = scheduledTask.getFrequency();

		Frequency frequency = Frequency.getByName(frequencyName);

		StringBundler cronExpressionSB = new StringBundler(8);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(startDate);

		int dayInt = Calendar.DATE;

		int yearInt = Calendar.YEAR;

		int monthInt = Calendar.MONTH;

		int day = calendar.get(dayInt);

		int month = calendar.get(monthInt) + 1;

		int year = calendar.get(yearInt);

		if (frequency.equals(Frequency.EXECUTE_ONCE)) {
			cronExpressionSB.append("0 0 ");
			cronExpressionSB.append(startHour);
			cronExpressionSB.append(" ");
			cronExpressionSB.append(day);
			cronExpressionSB.append(" ");
			cronExpressionSB.append(month);
			cronExpressionSB.append(" ? ");
			cronExpressionSB.append(year);
		}
		else if (frequency.equals(Frequency.HOURLY)) {
			cronExpressionSB.append("0 0 */2 ? * *");
		}
		else if (frequency.equals(Frequency.MONTHLY)) {
			cronExpressionSB.append("0 0 ");
			cronExpressionSB.append(startHour);
			cronExpressionSB.append(" ");
			cronExpressionSB.append(day);
			cronExpressionSB.append(" * ?");
		}
		else {
			cronExpressionSB.append("* * * * ? *");
		}

		return cronExpressionSB.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ScheduledTaskLocalServiceImpl.class);

	@ServiceReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

	@ServiceReference(type = SchedulerEngineHelper.class)
	private SchedulerEngineHelper _schedulerEngineHelper;

	@ServiceReference(type = TriggerFactory.class)
	private TriggerFactory _triggerFactory;

}