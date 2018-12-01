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

package com.liferay.commerce.data.integration.apio.resource.test.utils;

import com.liferay.commerce.currency.service.CommerceCurrencyLocalServiceUtil;
import com.liferay.commerce.product.service.CPMeasurementUnitLocalServiceUtil;
import com.liferay.commerce.service.CommerceCountryLocalServiceUtil;
import com.liferay.commerce.user.segment.service.CommerceUserSegmentEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Zoltán Takács
 */
public class CommerceTestSiteActivator {

	public static ServiceContext getServiceContext(long groupId)
		throws PortalException {

		User user = TestPropsValues.getUser();
		Group group = GroupLocalServiceUtil.getGroup(groupId);

		Locale locale = LocaleUtil.getSiteDefault();

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setAddGroupPermissions(true);
		serviceContext.setAddGuestPermissions(true);
		serviceContext.setCompanyId(group.getCompanyId());
		serviceContext.setLanguageId(LanguageUtil.getLanguageId(locale));
		serviceContext.setScopeGroupId(groupId);
		serviceContext.setTimeZone(user.getTimeZone());
		serviceContext.setUserId(user.getUserId());

		return serviceContext;
	}

	public static void initialize(long groupId) throws Exception {
		ServiceContext serviceContext = getServiceContext(groupId);

		_importDefaultCommerceCountries(serviceContext);
		_importDefaultCommerceCurrencies(serviceContext);
		_importSystemCommerceUserSegmentEntries(serviceContext);
		_importDefaultCPMeasurementUnits(serviceContext);
	}

	private static void _importDefaultCommerceCountries(
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing default commerce countries...");
		}

		CommerceCountryLocalServiceUtil.importDefaultCountries(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Commerce countries successfully imported");
		}
	}

	private static void _importDefaultCommerceCurrencies(
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Importing default commerce currencies...");
		}

		CommerceCurrencyLocalServiceUtil.importDefaultValues(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info("Commerce currencies successfully imported");
		}
	}

	private static void _importDefaultCPMeasurementUnits(
			ServiceContext serviceContext)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Importing commerce product measurement units...");
		}

		CPMeasurementUnitLocalServiceUtil.importDefaultValues(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Commerce product measurement units successfully imported");
		}
	}

	private static void _importSystemCommerceUserSegmentEntries(
			ServiceContext serviceContext)
		throws PortalException {

		if (_log.isInfoEnabled()) {
			_log.info("Importing system commerce user segment entries...");
		}

		CommerceUserSegmentEntryLocalServiceUtil.
			importSystemCommerceUserSegmentEntries(serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info(
				"System commerce user segment entries successfully imported");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceTestSiteActivator.class);

}