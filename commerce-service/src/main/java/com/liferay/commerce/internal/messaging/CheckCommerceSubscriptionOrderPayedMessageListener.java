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

package com.liferay.commerce.internal.messaging;

import com.liferay.commerce.internal.configuration.subscription.CommerceSubscriptionConfiguration;
import com.liferay.commerce.model.CommerceSubscriptionCycleEntry;
import com.liferay.commerce.model.CommerceSubscriptionEntry;
import com.liferay.commerce.service.CommerceSubscriptionCycleEntryLocalService;
import com.liferay.commerce.service.CommerceSubscriptionEntryLocalService;
import com.liferay.commerce.util.comparator.CommerceSubscriptionCycleEntryCreateDateComparator;
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
	configurationPid = "com.liferay.commerce.internal.configuration.subscription.CommerceSubscriptionConfiguration",
	immediate = true,
	service = CheckCommerceSubscriptionOrderPayedMessageListener.class
)
public class CheckCommerceSubscriptionOrderPayedMessageListener
	extends BaseMessageListener {

	@Activate
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		_commerceSubscriptionConfiguration =
			ConfigurableUtil.createConfigurable(
				CommerceSubscriptionConfiguration.class, properties);

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null,
			_commerceSubscriptionConfiguration.checkPayedOrderInterval(),
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
		List<CommerceSubscriptionEntry> activeCPSubscriptionEntries =
			_commerceSubscriptionEntryLocalService.
				getActiveCPSubscriptionEntries();

		for (CommerceSubscriptionEntry commerceSubscriptionEntry :
				activeCPSubscriptionEntries) {

			List<CommerceSubscriptionCycleEntry> cpSubscriptionCycleEntries =
				_commerceSubscriptionCycleEntryLocalService.
					getCPSubscriptionCycleEntries(
						commerceSubscriptionEntry.
							getCommerceSubscriptionEntryId(),
						QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						new CommerceSubscriptionCycleEntryCreateDateComparator());

			CommerceSubscriptionCycleEntry firstCommerceSubscriptionCycleEntry =
				cpSubscriptionCycleEntries.get(0);

			Date now = new Date();

			Date creationDate =
				firstCommerceSubscriptionCycleEntry.getCreateDate();

			Calendar calendar = GregorianCalendar.getInstance();

			calendar.setTime(creationDate);

			calendar.add(
				Calendar.MINUTE,
				_commerceSubscriptionConfiguration.payedOrderInterval());

			if (!(DateUtil.compareTo(calendar.getTime(), now) < 0)) {
				_commerceSubscriptionEntryLocalService.setActive(
					commerceSubscriptionEntry.getCommerceSubscriptionEntryId(),
					false);
			}
		}
	}

	private CommerceSubscriptionConfiguration
		_commerceSubscriptionConfiguration;

	@Reference
	private CommerceSubscriptionCycleEntryLocalService
		_commerceSubscriptionCycleEntryLocalService;

	@Reference
	private CommerceSubscriptionEntryLocalService
		_commerceSubscriptionEntryLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}