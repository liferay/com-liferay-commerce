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

package com.liferay.commerce.product.subscription.web.internal.messaging;

import com.liferay.commerce.model.CPSubscriptionCycleEntry;
import com.liferay.commerce.model.CPSubscriptionEntry;
import com.liferay.commerce.product.subscription.web.internal.configuration.subscription.CPSubscriptionConfiguration;
import com.liferay.commerce.service.CPSubscriptionCycleEntryLocalService;
import com.liferay.commerce.service.CPSubscriptionEntryLocalService;
import com.liferay.commerce.util.comparator.CPSubscriptionCycleEntryCreateDateComparator;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luca Pellizzon
 */
@Component(
	configurationPid = "com.liferay.commerce.product.subscription.web.internal.configuration.subscription.CPSubscriptionConfiguration",
	immediate = true,
	service = CheckCPSubscriptionOrderPayedMessageListener.class
)
public class CheckCPSubscriptionOrderPayedMessageListener
	extends BaseMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		_cpSubscriptionConfiguration = ConfigurableUtil.createConfigurable(
			CPSubscriptionConfiguration.class, properties);

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null,
			_cpSubscriptionConfiguration.checkPayedOrderInterval(),
			TimeUnit.MINUTE);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<CPSubscriptionEntry> activeCPSubscriptionEntries =
			_cpSubscriptionEntryLocalService.getActiveCPSubscriptionEntries();

		for (CPSubscriptionEntry cpSubscriptionEntry :
				activeCPSubscriptionEntries) {

			List<CPSubscriptionCycleEntry> cpSubscriptionCycleEntries =
				_cpSubscriptionCycleEntryLocalService.
					getCPSubscriptionCycleEntries(
						cpSubscriptionEntry.getCPSubscriptionEntryId(),
						QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						new CPSubscriptionCycleEntryCreateDateComparator());

			CPSubscriptionCycleEntry firstCPSubscriptionCycleEntry =
				cpSubscriptionCycleEntries.get(0);

			Date now = new Date();

			Date creationDate = firstCPSubscriptionCycleEntry.getCreateDate();

			Calendar calendar = GregorianCalendar.getInstance();

			calendar.setTime(creationDate);

			calendar.add(
				Calendar.MINUTE,
				_cpSubscriptionConfiguration.payedOrderInterval());

			if (!(DateUtil.compareTo(calendar.getTime(), now) < 0)) {
				_cpSubscriptionEntryLocalService.setActive(
					cpSubscriptionEntry.getCPSubscriptionEntryId(), false);
			}
		}
	}

	private CPSubscriptionConfiguration _cpSubscriptionConfiguration;

	@Reference
	private CPSubscriptionCycleEntryLocalService
		_cpSubscriptionCycleEntryLocalService;

	@Reference
	private CPSubscriptionEntryLocalService _cpSubscriptionEntryLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}